package com.youtube.model.vo;

import java.util.Date;

public class VideoLike {
	
	private int vLikeCode;
	private Date vLikeDate;
	private Video video; //VIDEO_CODE
	private Member memeber; //MEMBER_ID
	
	public VideoLike() {
	}

	public VideoLike(int vLikeCode, Date vLikeDate, Video video, Member memeber) {
		this.vLikeCode = vLikeCode;
		this.vLikeDate = vLikeDate;
		this.video = video;
		this.memeber = memeber;
	}

	public int getvLikeCode() {
		return vLikeCode;
	}

	public void setvLikeCode(int vLikeCode) {
		this.vLikeCode = vLikeCode;
	}

	public Date getvLikeDate() {
		return vLikeDate;
	}

	public void setvLikeDate(Date vLikeDate) {
		this.vLikeDate = vLikeDate;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Member getMemeber() {
		return memeber;
	}

	public void setMemeber(Member memeber) {
		this.memeber = memeber;
	}

	@Override
	public String toString() {
		return "VideoLike [vLikeCode=" + vLikeCode + ", vLikeDate=" + vLikeDate + ", video=" + video + ", memeber="
				+ memeber + "]";
	}
	
	
}
