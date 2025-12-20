import smtplib
from email.message import EmailMessage
import os

name = "Vaibhav Shukla"
semester = "5"
branch = "BTech Data Science"
roll_no = "500106855"

image_path = r"D:\git-work\interns-assignments\Vaibhav_Shukla\image.jpg"

if not os.path.exists(image_path):
    print("Image not found")
    exit()

msg = EmailMessage()
msg["From"] = "student@test.com"
msg["To"] = "hr@ignitershub.com"
msg["Subject"] = "Challenge 3 Completed"

msg.set_content(
    "Name: " + name + "\n"
    "Semester: " + semester + "\n"
    "Branch: " + branch + "\n"
    "Roll Number: " + roll_no
)

with open(image_path, "rb") as f:
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
