#!/bin/bash

#This script takes a mysql command and runs it against a series of databases.
#The results of the query are printed in the console.
#Timing data is stored in the generated CSV file (this is handy for making charts).

#################################################
############### Script Variables ################
#################################################

#ARG1: Name of output file (make it end in .csv)

COMMANDP1="LOAD DATA LOCAL INFILE '/media/lemmon/BigSSD/allData/DataSets/criminals_3opt_40M.txt' INTO TABLE IndexBase."
COMMANDP2=" ;"

DBS="Criminals_ar_nk_0_big"


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
