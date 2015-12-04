#!/bin/bash

#This script takes a mysql command and runs it against a series of databases.
#The results of the query are printed in the console.
#Timing data is stored in the generated CSV file (this is handy for making charts).

#################################################
############### Script Variables ################
#################################################

COMMAND='Select * from harre096.inventory;'

CSV_OUT_FILE=timingResults.csv
DBS="wideINNODB narrowINNODB wideARIA narrowARIA"


#################################################
########## Build  csv and run COMMAND ###########
#################################################

###Add csv headers
printf "\n%s, real, sys, usr\n" "$COMMAND" >> $CSV_OUT_FILE

for DB in $DBS
do
  ###Add name of each db to the new row
  printf '%s,' $DB >> $CSV_OUT_FILE

  ###Run test command and add times to the row
  ###Original formatting: "\t%e real, \t%U user,\t%S sys"
  /usr/bin/time  -a -o $CSV_OUT_FILE -f "%e,%U,%S"\
   mysql -u harre096 --password=bananas --host=ids -e "$COMMAND"
done
