package org.proj.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Image")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String url;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_added;

	@JsonIgnore
	@ManyToMany(mappedBy = "images")
	private Set<User> users = new HashSet<>();
/*
	@OneToMany (mappedBy = "image")
	private Set<Comment> comments = new LinkedHashSet<>();
*/
	@Column(name = "format")
	private String format;
	public Image() {

		url = "url";
	}

	public Image(String title, String url,String format) {
		this.title = title;
		this.url = url;
		this.format = format;
        this.date_added = new Date();
	}

	public Image(int id, String title, String url) {
		this.id = id;
		this.title = title;
		this.url = url;
	}

    public Image(String title, String url, Date date_added) {
        this.title = title;
        this.url = url;
        this.date_added = date_added;

	}

    @JsonIgnore
	public Set<User> getSet() {
		return users;
	}

	public void setSet(Set<User> set) {
		this.users = set;
	}

	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}

	public Date getDate_added() {
		return date_added;
	}

	@Override
	public String toString() {
		return "Image{" +
				"id=" + id +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", extension=" + format +
				", date_added=" + getDate_added() +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (this.hashCode() == o.hashCode()) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 + (url != null ? url.hashCode() : 0);
		return result;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
