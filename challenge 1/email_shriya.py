import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email import encoders
import os


smtp_host = "smtp.gmail.com"
smtp_port = 587
username = "shriyarai0223@gmail.com"
app_password = "mxgk oqxg lbrg tsvd"  


from_email = "shriyarai0223@gmail.com"
to_email = "hr@ignitershub.com"
subject = "Challenge 3 Completed"
body = """Hello Sir/Ma'am,

This is to notify you that Challenge 3 has been successfully completed.

Here are my details:
Name: Shriya Rai
Sem: VIII
Branch: Computer Engineering
Roll No: 21CSEC48

Best regards,  
Shriya
"""


msg = MIMEMultipart()
msg['From'] = from_email
msg['To'] = to_email
msg['Subject'] = subject
msg.attach(MIMEText(body, 'plain'))


image_path = "challenge 1/shriya.png"  # Ensure this file exists in your working directory

if os.path.isfile(image_path):
    try:
        with open(image_path, "rb") as attachment:
            part = MIMEBase("application", "octet-stream")
            part.set_payload(attachment.read())
        
        encoders.encode_base64(part)
        part.add_header(
            "Content-Disposition",
            f"attachment; filename={os.path.basename(image_path)}"
        )
        msg.attach(part)
    except Exception as e:
        print(f"Error attaching file: {e}")
        exit()
else:
    print(f"Error: The file '{image_path}' was not found.")
    exit()


try:
    with smtplib.SMTP(smtp_host, smtp_port) as smtplibObj:
        smtplibObj.ehlo()
        smtplibObj.starttls()
        smtplibObj.login(username, app_password)
        smtplibObj.sendmail(from_email, to_email, msg.as_string())
    print("✅ Email with attachment sent successfully!")
except smtplib.SMTPAuthenticationError:
    print("❌ Authentication failed. Please check your username and app password.")
except Exception as e:
    print(f"❌ Error: {e}")
