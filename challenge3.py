import smtplib
from email.message import EmailMessage
import os
from dotenv import load_dotenv  
load_dotenv()
sender_email = "freefiredjdk123@gmail.com"
app_password = os.getenv("app_password")


receiver_email = "hr@ignitershub.com"

msg = EmailMessage()

msg["Subject"] = "Challenge 3 Completed"
msg["From"] = sender_email
msg["To"] = receiver_email

body = """
Name: Divyanshu Puri
Semester: 7
Branch: CSE
This email is to inform you that I have successfully completed Challenge 3
"""

msg.set_content(body)

image_path = "image.jpg"

with open(image_path, "rb") as img:
    msg.add_attachment(
        img.read(),
        maintype="image",
        subtype="jpeg",
        filename="image.jpg"
    )

with smtplib.SMTP_SSL("smtp.gmail.com", 465) as smtp:
    smtp.login(sender_email, app_password)
    smtp.send_message(msg)

print("Email sent successfully!")
