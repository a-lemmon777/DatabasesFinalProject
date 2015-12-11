#!/bin/bash

#This script takes a mysql command and runs it against a series of databases.
#The results of the query are printed in the console.
#Timing data is stored in the generated CSV file (this is handy for making charts).

#################################################
############### Script Variables ################
#################################################

#ARG1: Name of output file (make it end in .csv)

COMMAND="LOAD DATA LOCAL INFILE '/home/lemmon/workspace/DatabasesFinalProject/DataSets/cars_max_1M.txt' INTO TABLE FinalProject."

DBS="cars_max_10M_blobAll cars_max_10M_blobAll_pk cars_max_10M_charAll cars_max_10M_charAll_pk cars_max_10M_textAll cars_max_10M_textAll_pk cars_max_10M_varcharAll cars_max_10M_varcharAll_pk"


#################################################
########## Build  csv and run COMMAND ###########
#################################################

###Add csv headers
printf "\n%s, real, sys, usr\n" "$COMMAND" > $1

for DB in $DBS
do
  ###Add name of each db to the new row
  printf '%s,' $DB >> $1

  ###Run test command and add times to the row
  ###Original formatting: "\t%e real, \t%U user,\t%S sys"
  /usr/bin/time  -a -o $1 -f "%e,%U,%S"\
   mysql -u root --password=bananas  -e "$COMMAND""$DB";
done
