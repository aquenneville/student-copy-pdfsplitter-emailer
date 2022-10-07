# student-copy-pdfsplitter-emailer

My teacher friend after an exam puts all the student copies into the scanner and generates a master pdf.<BR>
She grades and then manually splits the students copies and manually sends them by email. <BR>

This is my way to help her automate the process of splitting and emailing. 

## Usage
Run in an editor did not have a chance to make this a bit more portable.
1. Fill up under resources/class/1class.csv the details of each student
2. Create a folder resources/copies and add the master pdf document.
3. Open application.properties, fill up the mail properties 
4. Set a class and pdf for a year: 
- class.file.year.1=class/1class.csv 
- student.copy.class.level.1=copies/1copies.pdf
Make sure both files exists on disk

Before running set classLevel = N and sendEmails = true; // when ready to send the emails

## TO DO 
- Pass as arguments: class file and master pdf from the command line
- Add send Copies as optional 
- Add dry-run feature
