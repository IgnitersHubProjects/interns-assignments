import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.image import MIMEImage
import os

def send_email():

    sender_email = "nikhil7679@gmail.com"
    sender_password = "exna wxxk ijom wudb" 
    receiver_email = "hr@ignitershub.com"
    name = "Nikhil Sharma"
    semester = "7th"
    branch = "Computer Engineering"
    roll_number = "22CSEC27"
    image_path = "dp.png"
    msg = MIMEMultipart()
    msg['From'] = sender_email
    msg['To'] = receiver_email
    msg['Subject'] = "Challenge 3 Completed"
    body = f"""
        Name: {name}
        Semester: {semester}
        Branch: {branch}
        Roll Number: {roll_number}
    """
    msg.attach(MIMEText(body, 'plain'))
  
    with open(image_path, 'rb') as img_file:
        img = MIMEImage(img_file.read())
        img.add_header('Content-Disposition', 'attachment', filename=os.path.basename(image_path))
        msg.attach(img)
    with smtplib.SMTP('smtp.gmail.com', 587) as server:
        server.starttls()
        server.login(sender_email, sender_password)
        server.send_message(msg)
        print("Email sent successfully!")

send_email()