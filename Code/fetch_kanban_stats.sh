#!/bin/bash

PROJECT_NUM=2
OWNER="f171p"

echo "Hole Daten von GitHub Project Board #$PROJECT_NUM..."

json_data=$(gh project item-list $PROJECT_NUM --owner $OWNER --format json)

TOTAL=$(echo "$json_data" | jq '.totalCount')
TODO=$(echo "$json_data" | jq '[.items[] | select(.status=="Todo" or .status=="Open")] | length')
WIP=$(echo "$json_data" | jq '[.items[] | select(.status=="In Progress")] | length')
DONE=$(echo "$json_data" | jq '[.items[] | select(.status=="Done")] | length')

echo ""
echo "========================================================"
echo "STATUS ÜBERSICHT"
echo "========================================================"
echo "Gesamt Tasks: $TOTAL"
echo "Offen (Todo): $TODO"
echo "In Arbeit:    $WIP"
echo "Fertig:       $DONE"
echo "========================================================"
echo ""

echo "DETAIL-LISTE (WIP & TODO)"
echo "--------------------------------------------------------"
echo "Typ   | Status      | Tage  | Titel"
echo "------|-------------|-------|-------------------------"

echo "$json_data" | jq -r '
  .items[]
  | select(.status != "Done")
  | {
      type: (if .content.createdAt then "Issue" else "Draft" end),
      title: .content.title,
      status: .status,
      age_days: (if .content.createdAt then ((now - (.content.createdAt | fromdate)) / 86400 | floor) else -1 end)
    }
  | "\( .type ) | \( .status ) | \( if .age_days == -1 then "-" else .age_days end ) | \( .title )"
' | column -t -s "|"

echo ""
echo "--------------------------------------------------------"
echo "DURCHSCHNITTSWERTE (Nur echte Issues)"
echo "--------------------------------------------------------"

avg_days=$(echo "$json_data" | jq '
  [ .items[] | select(.status != "Done") | select(.content.createdAt != null) | ((now - (.content.createdAt | fromdate)) / 86400) ]
  | if length > 0 then (add / length | floor) else 0 end
')

max_days=$(echo "$json_data" | jq '
  [ .items[] | select(.status != "Done") | select(.content.createdAt != null) | ((now - (.content.createdAt | fromdate)) / 86400) ]
  | if length > 0 then (max | floor) else 0 end
')

echo "Durchschnittliches Alter: $avg_days Tage"
echo "Ältestes Ticket:          $max_days Tage"
echo "--------------------------------------------------------"
