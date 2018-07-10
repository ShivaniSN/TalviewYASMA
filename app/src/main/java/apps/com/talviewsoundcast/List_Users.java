package apps.com.talviewsoundcast;

import com.google.gson.annotations.SerializedName;

public class List_Users {
    @SerializedName("id")
    String stringId;
    @SerializedName("username")
    String stringUsername;

    public List_Users(){
        this.stringId = "";
        this.stringUsername = "";
    }

    public List_Users(String stringId,String stringUsername){
        this.stringId = stringId;
        this.stringUsername = stringUsername;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringUsername(String stringUsername) {
        this.stringUsername = stringUsername;
    }

    public String getStringUsername() {
        return stringUsername;
    }
}
