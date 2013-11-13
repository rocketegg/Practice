

## This script parse git status and allow you to reset all files that are currently cached or can take input (copy and paste)
## and will run git add [FILENAME] on the list of files

##Example input
##========================================================
#	modified:   norse/folk/norcaltda/www/cms/header.html
#	modified:   norse/folk/norcaltda/www/cms/index.css
#	modified:   norse/folk/norcaltda/www/cms/index.html
#	modified:   norse/folk/norcaltda/www/cms/index22.html
#	modified:   norse/folk/norcaltda/www/cms/indexnofb.html
#	modified:   norse/folk/norcaltda/www/cms/jqModal.css
#	modified:   norse/folk/norcaltda/www/cms/jquery-ui-timepicker-addon.js
#	modified:   norse/folk/norcaltda/www/cms/login.js
#	modified:   norse/folk/norcaltda/www/cms/main.js
#	modified:   norse/folk/norcaltda/www/cms/main2.js
#	modified:   norse/folk/norcaltda/www/cms/main_my.js
#	modified:   norse/folk/norcaltda/www/cms/mainpublish.html
#	modified:   norse/folk/norcaltda/www/cms/myaccount.js
#	modified:   norse/folk/norcaltda/www/cms/register.html
#	modified:   norse/folk/norcaltda/www/cms/register.js

#!/bin/sh
# Linux users have to change $8 to $9 

option=$1

#echo $option
if [ "$option" = "help" ] || [ "$option" = "" ] ; then 
	echo "This bash script runs git status and captures the files to either reset, add or add untracked."
	echo "    Use 'reset' to reset all files from HEAD.  (e.g. ./gitHelper.sh reset) "
	echo "    Use 'add' to add all files unstaged for commit."
	echo "    Use 'untracked' to add all untracked files to commit"
fi

#reset all files

if [ "$option" = "reset" ] ; then
	var=`git status -s | awk '/^([MA]|AM)[ ].*/{print $2}' | xargs -L1 echo `
	if [ "$var" = "" ] ; then
		echo "No added files to reset.  None reset from HEAD."
	else
		echo "RESETTING the files below: "
		echo "======================================================"
		echo "$var"
		echo "     "
		echo "Results: "
		echo "======================================================"
		var=`git status -s | awk '/^([MA]|AM)[ ].*/{print $2}' | xargs -L1 git reset HEAD -q `
		if [ "$var" != "" ] ; then 
			echo "$var"
		else
			var=`git status -s | awk '/^[ ]M[ ].*/{print "Unstaged for Commit: \033[0;31m" $2 "\033[0m"}'`
			echo "$var"
			var=`git status -s | grep ?? | awk '{print "Untracked: \033[0;34m" $2 "\033[0m"}'`
			echo "$var"
		fi
	fi
fi

#add all modified files 
if [ "$option" = "add" ] ; then
	var=`git status -s | awk '/^[ ]M[ ].*/{print $2}' | xargs -L1 echo `
	if [ "$var" = "" ] ; then
		echo "No modified files to add to git.  None added."
	else
		echo "ADDING the files below: "
		echo "======================================================"
		echo "$var"
		echo "     "
		echo "Results: "
		echo "======================================================"
		var=`git status -s | awk '/^[ ]M[ ].*/{print $2}' | xargs -L1 git add `
		var=`git status -s | awk '/^([MA]|AM)[ ].*/{print "Staged for Commit: \033[0;32m" $2 "\033[0m"}' `
		echo "$var"
	fi
fi

#add all untracked files
if [ "$option" = "untracked" ] ; then
	var=`git status -s | grep ?? | awk '{print $2}' | xargs -L1 echo`
	if [ "$var" = "" ] ; then
		echo "No untracked files to add.  None added."
	else
		echo "ADDING the untracked files below: "
		echo "======================================================"
		echo "$var"
		echo "     "
		echo "Results: "
		echo "======================================================"
		var=`git status -s | grep ?? | awk '{print $2}' | xargs -L1 git add `
		var=`git status -s | awk '/^([MA]|AM)[ ].*/{print "Staged for Commit: \033[0;34m" $2 "\033[0m"}' `
		echo "$var"
	fi
fi

