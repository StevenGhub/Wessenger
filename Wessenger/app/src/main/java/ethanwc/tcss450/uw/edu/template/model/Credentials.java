package ethanwc.tcss450.uw.edu.template.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Class to encapsulate credentials fields. Building an Object requires a email and password.
 *
 * Optional fields include username, first and last name.
 *
 *
 * @author Charles Bryan
 * @version 8 January 2019
 */
public class Credentials implements Serializable {
    private static final long serialVersionUID = -1634677417576883013L;

    private final String mUsername;
    private final String mPassword;
    private final String mFirstName;
    private final String mLastName;
    private final String mEmail;
    private final int mCode;
    private final String mtempPwd;
    private final String mChangePassword;
    private final int mErrorCount;
    private final int mVerify;
    private final String mEmail2;
    private final String mChatId;



    /**
     * Helper class for building Credentials.
     *
     * @author Charles Bryan
     */
    public static class Builder {

        private String mPassword;
        private final String mEmail;

        private String mFirstName = "";
        private String mLastName = "";
        private String mUsername = "";
        private int mCode = 0;
        private String mtempPwd ="";
        private String mChangePassword = "";
        private int mErrorCount = 0;
        private int mVerify = 0;
        private String mEmail2 = "";
        private String mChatId = "";


        /**
         * Constructs a new Builder.
         *
         * No validation is performed. Ensure that the argument is a
         * valid email before adding here if you wish to perform validation.
         *
         * @param email the email
         * @param password the password
         */
        public Builder(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        /**
         * Constructs a new Builder.
         *
         * No validation is performed. Ensure that the argument is a
         * valid email before adding here if you wish to perform validation.
         *
         * @param email the email

         */
        public Builder(String email) {
            mEmail = email;

        }


        /**
         * Add an optional first name.
         * @param val an optional first name
         * @return
         */
        public Builder addFirstName(final String val) {
            mFirstName = val;
            return this;
        }

        public Builder addErrorCount(final int val) {
            mErrorCount = val;
            return this;
        }

        /**
         * Add an optional last name.
         * @param val an optional last name
         * @return
         */
        public Builder addLastName(final String val) {
            mLastName = val;
            return this;
        }

        /**
         * Add an optional Uuername.
         * @param val an optional Uuername
         * @return
         */
        public Builder addUsername(final String val) {
            mUsername = val;
            return this;
        }

        public Builder addCode(final int val) {
            mCode = val;
            return this;
        }
        public Builder addtempPwd(final String val) {
            mtempPwd = val;
            return this;
        }

        public Builder addEmail2(final String val) {
            mEmail2 = val;
            return this;
        }

        public Builder addVerify(final int val) {
            mVerify = val;
            return this;
        }

        public Builder addChatId(final String val) {
            mChatId = val;
            return this;
        }

        public Builder addChangePassword(final String val) {
            mChangePassword = val;
            return this;
        }

        public Credentials build() {
            return new Credentials(this);
        }
    }

    /**
     * Construct a Credentials internally from a builder.
     *
     * @param builder the builder used to construct this object
     */
    private Credentials(final Builder builder) {
        mUsername = builder.mUsername;
        mPassword = builder.mPassword;
        mFirstName = builder.mFirstName;
        mLastName = builder.mLastName;
        mEmail = builder.mEmail;
        mCode = builder.mCode;
        mtempPwd = builder.mtempPwd;
        mChangePassword = builder.mChangePassword;
        mErrorCount = builder.mErrorCount;
        mEmail2 = builder.mEmail2;
        mVerify = builder.mVerify;
        mChatId = builder.mChatId;

    }

    /**
     * Get the Username.
     * @return the username
     */
    public String getUsername() {
        return mUsername;
    }

    /**
     * Get the password.
     * @return the password
     */
    public String getPassword() {
        return mPassword;
    }

    public int getErrorCount() {
        return mErrorCount;
    }
    /**
     * Get the first name or the empty string if no first name was provided.
     * @return the first name or the empty string if no first name was provided.
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * Get the last name or the empty string if no first name was provided.
     * @return the last name or the empty string if no first name was provided.
     */
    public String getLastName() {
        return mLastName;
    }

    public String getEmail2() {return mEmail2;}

    public int getVerify() { return mVerify;}
    /**
     * Get the email or the empty string if no first name was provided.
     * @return the email or the empty string if no first name was provided.
     */
    public String getEmail() {
        return mEmail;
    }

    public int getCode() { return mCode; }

    public String getTempPwd() { return mtempPwd; }


    public String getChangePassword() {return mChangePassword;}

    public String getChatId() { return mChatId;}

    /**
     * Get all of the fields in a single JSON object. Note, if no values were provided for the
     * optional fields via the Builder, the JSON object will include the empty string for those
     * fields.
     *
     * Keys: username, password, first, last, email
     *
     * @return all of the fields in a single JSON object
     */
    public JSONObject asJSONObject() {
        //build the JSONObject
        JSONObject msg = new JSONObject();
        try {
            msg.put("username", getUsername());
            msg.put("password", mPassword);
            msg.put("first", getFirstName());
            msg.put("last", getLastName());
            msg.put("email", getEmail());
            msg.put("code", getCode());
            msg.put("tempPwd", getTempPwd());
            msg.put("changepassword", getChangePassword());
            msg.put("error", getErrorCount());
            msg.put("email2", getEmail2());
            msg.put("verify", getVerify());
            msg.put("chatid", getChatId());

        } catch (JSONException e) {
            Log.wtf("CREDENTIALS", "Error creating JSON: " + e.getMessage());
        }
        return msg;
    }

}
