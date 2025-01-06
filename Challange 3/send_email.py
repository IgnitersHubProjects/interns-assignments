import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.image import MIMEImage
from email.mime.base import MIMEBase
from email import encoders
import os

def send_email_with_attachment():
    # Email details
    sender_email = "ak2575606@gmail.com"  # Replace with your email address
    receiver_email = "hr@ignitershub.com"
    password = "ytmu kics mhlp wfag"  # Replace with your email account password
    subject = "Challenge 3 Completed"
    
    # Email body content
    body = """
    Name: Anil Kumar Pandit
    Semester: 8th
    Branch: Computer Science
    Roll Number: 21CSEC31
    
    Attached is an image as per the requirements.
    """
    
    # Set up the MIME
    msg = MIMEMultipart()
    msg['From'] = sender_email
    msg['To'] = receiver_email
    msg['Subject'] = subject
    
    # Attach the email body
    msg.attach(MIMEText(body, 'plain'))
    
    # Attach the image
    image_path = "image.jpg"  # Replace with your image path
    if os.path.exists(image_path):
        with open(image_path, 'rb') as file:
            img_attachment = MIMEImage(file.read())
            img_attachment.add_header('Content-Disposition', 'attachment', filename=os.path.basename(image_path))
            msg.attach(img_attachment)
    else:
        print("Image file not found.")
        return

    # Set up the SMTP server and send email
    try:
        # Gmail's SMTP server (can be replaced with another server if needed)
        with smtplib.SMTP('smtp.gmail.com', 587) as server:
            server.starttls()  # Use TLS encryption
            server.login(sender_email, password)
            server.sendmail(sender_email, receiver_email, msg.as_string())
            print("Email sent successfully!")
    except Exception as e:
        print(f"Error occurred: {e}")

if __name__ == "__main__":
    send_email_with_attachment()
