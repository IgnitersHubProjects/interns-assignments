import smtplib                           # importing necessary module for sending email via SMTP protocol 
from email.message import EmailMessage   # importing necessary modules for email creation and sending
import os      # importing os module to handle file paths and check file existence 

name = "Vaibhav Shukla"
semester = "7th"
branch = "BTech Data Science"
roll_no = "500106855"

image_path = r"D:\git-work\interns-assignments\Vaibhav_Shukla\image.jpg"

if not os.path.exists(image_path):               # checking if image file exists 
    print("Image not found")
    exit()

msg = EmailMessage()                           # creating email message object 
msg["From"] = "vaibhavshukl23@gmail.com"
msg["To"] = "hr@ignitershub.com"
msg["Subject"] = "Challenge 3 Completed"

msg.set_content(                                  # setting the email content 
    "Name: " + name + "\n"
    "Semester: " + semester + "\n"
    "Branch: " + branch + "\n"     # + shows concatenation of strings 
    "Roll Number: " + roll_no
)

with open(image_path, "rb") as f:  # opening the image file in binary mode 
    msg.add_attachment(
        f.read(),
        maintype="image",
        subtype="jpeg",
        filename="image.jpg"
    )

server = smtplib.SMTP("localhost", 1025)
server.send_message(msg)
server.quit()

print("Email sent")

#use this in terminal     python -m aiosmtpd -n -l localhost:1025
#run the code 
#Due to strict security restrictions on Gmail and Outlook, automated SMTP authentication was blocked during development.
#Therefore, a local SMTP debugging server was used by me as a last resort to demonstrate programmatic email triggering.
