import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email import encoders


smtp_host = "smtp.gmail.com"
smtp_port = 587
username = "sisirrai2407@gmail.com"
app_password = "aelrzfribnmweiam" 


from_email = "sisirrai2407@gmail.com"
to_email = "hr@ignitershub.com"
subject = "Challenge 3 Completed"
body = "Hello,Sir/Ma'am \n\nThis is to notify you that Challenge 3 has been successfully completed.\n\n Here are my details\n\nName: Sisir Rai\nSem: 8th\nBranch: Computer Engineering\nRoll: 21CSEC45\n\n\nBest regards,\nSisir"


msg = MIMEMultipart()
msg['From'] = from_email
msg['To'] = to_email
msg['Subject'] = subject


msg.attach(MIMEText(body, 'plain'))


image_path = "connection.jpg"  
try:
    with open(image_path, "rb") as attachment:
        part = MIMEBase("application", "octet-stream")
        part.set_payload(attachment.read())

    encoders.encode_base64(part)
    part.add_header(
        "Content-Disposition",
        f"attachment; filename={image_path.split('connection.png')[-1]}"  
    )
    msg.attach(part)
except FileNotFoundError:
    print(f"Error: The file at '{image_path}' was not found.")
    exit()


try:
    smtplibObj = smtplib.SMTP(smtp_host, smtp_port)
    smtplibObj.ehlo()  
    smtplibObj.starttls()  
    smtplibObj.login(username, app_password)
    smtplibObj.sendmail(from_email, to_email, msg.as_string())
    print("Email with attachment sent successfully!")
except Exception as e:
    print(f"Error: {e}")
finally:
    smtplibObj.quit()
