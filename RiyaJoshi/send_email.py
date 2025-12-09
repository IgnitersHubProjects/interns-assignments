import smtplib
from email.message import EmailMessage
import mimetypes
import os

# ---------------- Configuration ----------------
sender_email = "riyajoshi1325@gmail.com"
password = "yeql jhhz vgej phtm"  # Replace with your Gmail App Password
receiver_email = "hr@ignitershub.com"

# Your details
name = "Riya Joshi"
semester = "7"
branch = "CSE"
roll_number = "220180101041"

# Path to image attachment
image_path = r"C:\Users\Riya Joshi\OneDrive\Desktop\IgnitersHub\image.png"

# Create the email message
msg = EmailMessage()
msg['Subject'] = "Challenge 3 Completed"
msg['From'] = sender_email
msg['To'] = receiver_email

# Email body
msg.set_content(f"""
Hello,

My details:
Name: {name}
Semester: {semester}
Branch: {branch}
Roll Number: {roll_number}

This email is sent as part of Challenge 3 submission.
""")

# Attach the image
if os.path.isfile(image_path):
    mime_type, _ = mimetypes.guess_type(image_path)
    if mime_type is None:
        mime_type = "application/octet-stream"
    maintype, subtype = mime_type.split('/', 1)
    
    with open(image_path, "rb") as img:
        msg.add_attachment(img.read(), maintype=maintype, subtype=subtype, filename=os.path.basename(image_path))
else:
    print(f"Attachment not found at {image_path}")

# Send the email via SMTP
try:
    with smtplib.SMTP_SSL('smtp.gmail.com', 465) as server:
        server.login(sender_email, password)
        server.send_message(msg)
    print("Email sent successfully!")
except Exception as e:
    print(f"Failed to send email: {e}")
