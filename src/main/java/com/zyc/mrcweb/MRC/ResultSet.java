package com.zyc.mrcweb.MRC;

public class ResultSet {
    private String answer="";
    private double score=0.0;
    private int start=0;
    private int end=0;

    public ResultSet(String answer, double probability, int start, int end){
        this.answer=answer;
        this.score=probability;
        this.start=start;
        this.end=end;
    }

    public ResultSet(){

    }

    public String getAnswer(){
        return answer;
    }

    public double getScore() {
        return score;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
