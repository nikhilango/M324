provider "aws" {
  region = "us-east-1"
}

resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_subnet" "sub1" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.1.0/24"
  availability_zone = "us-east-1a"
}

resource "aws_subnet" "sub2" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.2.0/24"
  availability_zone = "us-east-1b"
}

# Internet-Gateway (Der Router nach draußen)
resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.main.id
}

# Route Table (Sagt dem Server: Geh über das Gateway ins Internet)
resource "aws_route_table" "rt" {
  vpc_id = aws_vpc.main.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.gw.id
  }
}

resource "aws_route_table_association" "a" {
  subnet_id      = aws_subnet.sub1.id
  route_table_id = aws_route_table.rt.id
}

resource "aws_route_table_association" "b" {
  subnet_id      = aws_subnet.sub2.id
  route_table_id = aws_route_table.rt.id
}

resource "aws_security_group" "web_sg" {
  name        = "web-server-sg"
  description = "Allow HTTP and SSH"
  vpc_id      = aws_vpc.main.id

  ingress {
    description = "HTTP from World"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Custom TCP 8080"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_lb" "app_alb" {
  name               = "p4-alb"
  load_balancer_type = "application"
  subnets            = [aws_subnet.sub1.id, aws_subnet.sub2.id]
  security_groups    = [aws_security_group.web_sg.id]
}

resource "aws_lb_target_group" "blue" {
  name     = "tg-employees-blue"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id
  health_check {
    path = "/api/employees"
  }
}

resource "aws_lb_target_group" "green" {
  name     = "tg-employees-green"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id
}

resource "aws_lb_listener" "http" {
  load_balancer_arn = aws_lb.app_alb.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type = "forward"
    forward {
      target_group {
        arn    = aws_lb_target_group.blue.arn
        weight = 100
      }
      target_group {
        arn    = aws_lb_target_group.green.arn
        weight = 0
      }
    }
  }
}

resource "aws_instance" "blue" {
  ami           = "ami-0c7217cdde317cfec"
  instance_type = "t3.micro"
  subnet_id     = aws_subnet.sub1.id
  associate_public_ip_address = true
  vpc_security_group_ids = [aws_security_group.web_sg.id]
  user_data     = file("cloud-config.yaml")

  iam_instance_profile = "LabInstanceProfile"

  key_name = aws_key_pair.debug.key_name
  tags = { Name = "App-Blue" }
}

resource "aws_instance" "green" {
  ami           = "ami-0c7217cdde317cfec"
  instance_type = "t3.micro"
  subnet_id     = aws_subnet.sub2.id
  associate_public_ip_address = true
  vpc_security_group_ids = [aws_security_group.web_sg.id]
  user_data     = file("cloud-config.yaml")

  iam_instance_profile = "LabInstanceProfile"

  key_name = aws_key_pair.debug.key_name
  tags = { Name = "App-Green" }
}

resource "aws_lb_target_group_attachment" "blue" {
  target_group_arn = aws_lb_target_group.blue.arn
  target_id        = aws_instance.blue.id
  port             = 8080
}

resource "aws_lb_target_group_attachment" "green" {
  target_group_arn = aws_lb_target_group.green.arn
  target_id        = aws_instance.green.id
  port             = 8080
}

resource "aws_key_pair" "debug" {
  key_name   = "debug-key"
  public_key = file("debug_key.pub")
}

output "alb_dns_name" {
  value       = aws_lb.app_alb.dns_name
  description = "The DNS name of the Application Load Balancer"
}

output "blue_ip" {
  value = aws_instance.blue.public_ip
}

output "green_ip" {
  value = aws_instance.green.public_ip
}
