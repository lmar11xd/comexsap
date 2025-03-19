package com.caasa.comexsap.exportaciones.model.to;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import jcifs.smb.SmbFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailMessage {

	private String subject;
	private String textMessage;
	private List<String> mailTo;
	private List<String> mailCC;
	private List<File> attachments;
	private transient List<SmbFile> attachmentsStream;
	
	private void writeObject(ObjectOutputStream oos) 
      throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(attachmentsStream);
    }
 
    private void readObject(ObjectInputStream ois) 
      throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public List<String> getMailTo() {
		return mailTo;
	}

	public void setMailTo(List<String> mailTo) {
		this.mailTo = mailTo;
	}

	public List<String> getMailCC() {
		return mailCC;
	}

	public void setMailCC(List<String> mailCC) {
		this.mailCC = mailCC;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

	public List<SmbFile> getAttachmentsStream() {
		return attachmentsStream;
	}

	public void setAttachmentsStream(List<SmbFile> attachmentsStream) {
		this.attachmentsStream = attachmentsStream;
	}

}
