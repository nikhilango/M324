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

resource "aws_lb" "app_alb" {
  name               = "p4-load-balancer"
  load_balancer_type = "application"
  subnets            = [aws_subnet.sub1.id, aws_subnet.sub2.id]
}

resource "aws_lb_target_group" "blue" {
  name     = "tg-blue"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id
  health_check {
    path = "/api/employees"
  }
}

resource "aws_lb_target_group" "green" {
  name     = "tg-green"
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
  ami           = "ami-0e35ddab05955cf57"
  instance_type = "t3.micro"
  subnet_id     = aws_subnet.sub1.id
  user_data     = file("cloud-config.yaml")

  iam_instance_profile = "LabInstanceProfile"

  tags = { Name = "App-Blue" }
}

resource "aws_instance" "green" {
  ami           = "ami-0e35ddab05955cf57"
  instance_type = "t3.micro"
  subnet_id     = aws_subnet.sub2.id
  user_data     = file("cloud-config.yaml")

  iam_instance_profile = "LabInstanceProfile"

  tags = { Name = "App-Green" }
}
