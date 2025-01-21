import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email import encoders
import os


def send_email():
    # Email configuration
    sender_email = "your_email@gmail.com"  # Replace with your email
    sender_password = "your_password"  # Replace with your app password
    recipient_email = "hr@ignitershub.com"
    subject = "Challenge 3 Completed"

    # Email body content
    body = """
    Hello,

    My details are as follows:
    Name: [Your Name]
    Semester: [Your Semester]
    Branch: [Your Branch]
    Roll Number: [Your Roll Number]

    Please find the attached image as required.

    Best regards,
    [Your Name]
    """

    # Create the email message
    msg = MIMEMultipart()
    msg['From'] = sender_email
    msg['To'] = recipient_email
    msg['Subject'] = subject
    msg.attach(MIMEText(body, 'plain'))

    # File attachment
    file_path = "path_to_your_image.png"  # Replace with the path to your image file
    if os.path.isfile(file_path) and file_path.lower().endswith(('.png', '.jpg', '.jpeg')):
        with open(file_path, 'rb') as attachment:
            part = MIMEBase('application', 'octet-stream')
            part.set_payload(attachment.read())
            encoders.encode_base64(part)
            part.add_header(
                'Content-Disposition',
                f'attachment; filename={os.path.basename(file_path)}'
            )
            msg.attach(part)
    else:
        print("Invalid file or unsupported format. Please attach a PNG, JPG, or JPEG image.")
        return

    # Sending the email
    try:
        with smtplib.SMTP('smtp.gmail.com', 587) as server:
            server.starttls()
            server.login(sender_email, sender_password)
            server.sendmail(sender_email, recipient_email, msg.as_string())
            print("Email sent successfully!")
    except Exception as e:
        print(f"Error sending email: {e}")


# Run the function
send_email()
