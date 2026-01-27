import os
import mimetypes
import smtplib
from email.message import EmailMessage

# =========================
# SETTINGS
# =========================
DRY_RUN = True   # ✅ Keep True in GitHub. Set False only when YOU want to send mail.

SENDER_EMAIL = "joshiharshit0710@gmail.com"
RECEIVER_EMAIL = "hr@ignitershub.com"
SUBJECT = "Challenge 3 Completed"

NAME = "Harshit Joshi"
SEMESTER = "7th"
BRANCH = "CSE"
ROLL_NO = "220050101042"

IMAGE_PATH = "FORM IMAGE.jpg"
ALLOWED_IMAGE_TYPES = {"image/png", "image/jpeg"}


def validate_image_file(image_path: str):
    if not os.path.isfile(image_path):
        raise FileNotFoundError(f"Attachment file not found: {image_path}")

    mime_type, _ = mimetypes.guess_type(image_path)
    if mime_type not in ALLOWED_IMAGE_TYPES:
        raise ValueError("Only PNG, JPG, JPEG images are allowed.")

    return mime_type


def build_email():
    mime_type = validate_image_file(IMAGE_PATH)

    msg = EmailMessage()
    msg["From"] = SENDER_EMAIL
    msg["To"] = RECEIVER_EMAIL
    msg["Subject"] = SUBJECT

    body = f"""
Hello HR Team,

Challenge 3 Completed.

Details:
Name: {NAME}
Semester: {SEMESTER}
Branch: {BRANCH}
Roll Number: {ROLL_NO}

Regards,
{NAME}
""".strip()

    msg.set_content(body)

    with open(IMAGE_PATH, "rb") as f:
        img_data = f.read()

    maintype, subtype = mime_type.split("/")
    msg.add_attachment(
        img_data,
        maintype=maintype,
        subtype=subtype,
        filename=os.path.basename(IMAGE_PATH)
    )

    return msg


def send_email(msg: EmailMessage, app_password: str):
    with smtplib.SMTP_SSL("smtp.gmail.com", 465) as smtp:
        smtp.login(SENDER_EMAIL, app_password)
        smtp.send_message(msg)


if __name__ == "__main__":
    try:
        msg = build_email()

        if DRY_RUN:
            print("✅ DRY RUN MODE (No email sent).")
            print("✅ Email created successfully with subject, body, and attachment.")
            print(f"To: {msg['To']}")
            print(f"Subject: {msg['Subject']}")
            print(f"Attachment: {IMAGE_PATH}")
        else:
            app_password = input("Enter Gmail App Password: ").strip()
            send_email(msg, app_password)
            print("✅ Email sent successfully to hr@ignitershub.com")

    except Exception as e:
        print(f"❌ Failed: {e}")

