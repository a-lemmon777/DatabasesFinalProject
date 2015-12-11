#!/bin/bash

#This script takes a mysql command and runs it against a series of databases.
#The results of the query are printed in the console.
#Timing data is stored in the generated CSV file (this is handy for making charts).

#################################################
############### Script Variables ################
#################################################

#ARG1: Name of output file (make it end in .csv)

COMMANDP1="select SSN, height, weight, fitnessLevel from IndexBase."
COMMANDP2=" where height = 70 and weight > 160;"

DBS="Criminals_ar_nk_0 Criminals_ar_nk_1 Criminals_ar_pk_1 Criminals_ar_pk_3 Criminals_in_nk_0 Criminals_in_nk_1 Criminals_in_pk_1 Criminals_in_pk_3"


#################################################
########## Build  csv and run COMMAND ###########
#################################################

###Add csv headers
printf '\n\"%s_%s\", seconds,\n' "$COMMANDP1" "$COMMANDP2" >> $1

for DB in $DBS
do
  ###Add name of each db to the new row
  printf '%s,' $DB >> $1

  ###Run test command and add times to the row
  ###Original formatting: "\t%e real, \t%U user,\t%S sys"

				 /usr/bin/time  -a -o $1 -f "%e"\
	 				mysql -u root --password=bananas  -e "$COMMANDP1""$DB""$COMMANDP2" > /dev/null

done
