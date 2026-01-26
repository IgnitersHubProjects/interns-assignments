import os
import mimetypes
import smtplib
from email.message import EmailMessage

# =========================
# CONFIG (EDIT THESE ONCE)
# =========================

SENDER_EMAIL = "joshiharshit0710@gmail.com"      # ✅ Your email
APP_PASSWORD = "mffp zovu huih ywey "    # ✅ Gmail App Password (NOT normal password)

RECEIVER_EMAIL = "hr@ignitershub.com"

SUBJECT = "Challenge 3 Completed"

NAME = "Harshit Joshi"
SEMESTER = "7th"
BRANCH = "CSE"
ROLL_NO = "220050101042"

# Put your image in same folder as this code OR give full path
IMAGE_PATH = "FORM IMAGE.jpg"  # ✅ Example: "photo.jpg" / "image.png"


# ✅ Only allow these image types
ALLOWED_IMAGE_TYPES = {"image/png", "image/jpeg"}


def validate_image_file(image_path: str):
    if not os.path.isfile(image_path):
        raise FileNotFoundError(f"Attachment file not found: {image_path}")

    mime_type, _ = mimetypes.guess_type(image_path)
    if mime_type not in ALLOWED_IMAGE_TYPES:
        raise ValueError("Only PNG, JPG, JPEG images are allowed.")

    return mime_type


def send_email():
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

    # Attach image
    with open(IMAGE_PATH, "rb") as f:
        img_data = f.read()

    maintype, subtype = mime_type.split("/")
    msg.add_attachment(
        img_data,
        maintype=maintype,
        subtype=subtype,
        filename=os.path.basename(IMAGE_PATH)
    )

    # Gmail SMTP
    with smtplib.SMTP_SSL("smtp.gmail.com", 465) as smtp:
        smtp.login(SENDER_EMAIL, APP_PASSWORD)
        smtp.send_message(msg)

    print("✅ Email sent successfully to hr@ignitershub.com")


if __name__ == "__main__":
    try:
        send_email()
    except Exception as e:
        print(f"❌ Failed: {e}")
