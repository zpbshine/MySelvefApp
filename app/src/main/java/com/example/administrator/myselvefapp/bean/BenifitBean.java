package com.example.administrator.myselvefapp.bean;

import java.util.ArrayList;

public class BenifitBean {
	public String market;
	public String olderPrice;
	public String nowPrice;
	public String content;
	public ArrayList<String> imageList ;
	public Comment comment;
	public ArrayList<Comment> comments;
	
	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getOlderPrice() {
		return olderPrice;
	}

	public void setOlderPrice(String olderPrice) {
		this.olderPrice = olderPrice;
	}

	public String getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<String> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<String> imageList) {
		this.imageList = imageList;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "BenifitBean [market=" + market + ", olderPrice=" + olderPrice
				+ ", nowPrice=" + nowPrice + ", content=" + content
				+ ", imageList=" + imageList + ", comment=" + comment
				+ ", comments=" + comments + "]";
	}

	public class Comment{
		public String photoImage;
		public String comContent;
		public String pubTime;
		public String getPhotoImage() {
			return photoImage;
		}
		public void setPhotoImage(String photoImage) {
			this.photoImage = photoImage;
		}
		public String getComContent() {
			return comContent;
		}
		public void setComContent(String comContent) {
			this.comContent = comContent;
		}
		public String getPubTime() {
			return pubTime;
		}
		public void setPubTime(String pubTime) {
			this.pubTime = pubTime;
		}
		@Override
		public String toString() {
			return "Comment [photoImage=" + photoImage + ", comContent="
					+ comContent + ", pubTime=" + pubTime + "]";
		}
	}
}
