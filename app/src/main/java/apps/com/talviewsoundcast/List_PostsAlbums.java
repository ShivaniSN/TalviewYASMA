package apps.com.talviewsoundcast;

import com.google.gson.annotations.SerializedName;

public class List_PostsAlbums {
    @SerializedName("id")
    String stringId;
    @SerializedName("title")
    String stringTitle;
    @SerializedName("body")
    String stringBody;
    @SerializedName("userId")
    String stringUserId;
    @SerializedName("name")
    String stringName;
    @SerializedName("url")
    String stringURL;
    @SerializedName("thumbnailUrl")
    String stringThumbnailURL;

    public List_PostsAlbums(){
        this.stringId = "";
        this.stringTitle = "";
        this.stringBody = "";
        this.stringUserId = "";
        this.stringName = "";
        this.stringURL = "";
        this.stringThumbnailURL = "";
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getStringTitle() {
        return stringTitle;
    }

    public void setStringTitle(String stringTitle) {
        this.stringTitle = stringTitle;
    }

    public String getStringUserId() {
        return stringUserId;
    }

    public void setStringUserId(String stringUserId) {
        this.stringUserId = stringUserId;
    }

    public String getStringBody() {
        return stringBody;
    }

    public void setStringBody(String stringBody) {
        this.stringBody = stringBody;
    }

    public String getStringName() {
        return stringName;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public String getStringThumbnailURL() {
        return stringThumbnailURL;
    }

    public void setStringThumbnailURL(String stringThumbnailURL) {
        this.stringThumbnailURL = stringThumbnailURL;
    }

    public String getStringURL() {
        return stringURL;
    }

    public void setStringURL(String stringURL) {
        this.stringURL = stringURL;
    }
}
