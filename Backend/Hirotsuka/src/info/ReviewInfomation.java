package info;

import java.util.HashMap;

import managers.Utils;

public class ReviewInfomation {
    UserInformation reviewer;
    int score;
    String comment;

    public ReviewInfomation(){}

    public ReviewInfomation(String json){
        var map = Utils.mapJson(json);
    }

    public String toJson(){
        HashMap<String,String> toMap = new HashMap<>();
        if (!(reviewer == null)){
            toMap.put("Reviewer", reviewer.toJson());
        }
        if (!(comment == null || comment.isBlank())){
            toMap.put("Comment", comment);
        }
         toMap.put("Score", Integer.toString(score));

         return Utils.toJson(toMap);
    }

    public UserInformation getReviewer() {
        return reviewer;
    }
    public int getScore() {
        return score;
    }
    public String getComment() {
        return comment;
    }
    public void setReviewer(UserInformation reviewer) {
        this.reviewer = reviewer;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    
    
}
