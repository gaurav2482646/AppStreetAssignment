package com.assignment.github.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Project implements Parcelable {

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
    private String username;
    private String type;
    private String url;
    private String avatar;
    private RepoBean repo;

    public Project() {
    }

    protected Project(Parcel in) {
        this.username = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.avatar = in.readString();
        this.repo = in.readParcelable(RepoBean.class.getClassLoader());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RepoBean getRepo() {
        return repo;
    }

    public void setRepo(RepoBean repo) {
        this.repo = repo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeString(this.avatar);
        dest.writeParcelable(this.repo, flags);
    }

    public static class RepoBean implements Parcelable {
        public static final Creator<RepoBean> CREATOR = new Creator<RepoBean>() {
            @Override
            public RepoBean createFromParcel(Parcel source) {
                return new RepoBean(source);
            }

            @Override
            public RepoBean[] newArray(int size) {
                return new RepoBean[size];
            }
        };
        private String name;
        private String description;
        private String url;

        public RepoBean() {
        }

        protected RepoBean(Parcel in) {
            this.name = in.readString();
            this.description = in.readString();
            this.url = in.readString();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.description);
            dest.writeString(this.url);
        }
    }
}
