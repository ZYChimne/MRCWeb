package com.zyc.mrcweb.MRC;

import com.google.gson.Gson;

import java.io.*;

public class MRC implements MRCInterface {
    QuestionSet qs=null;
    public MRC(QuestionSet qs){
        this.qs=qs;
    }

    public ResultSet getAnswer() {
        String answer = null;
        ResultSet ans=null;
        try {
            File f=new File("tmp.json");
            BufferedReader br = new BufferedReader(new FileReader(f));
            answer = br.readLine();
            Gson gson=new Gson();
            ans=gson.fromJson(answer, ResultSet.class);
            br.close();
            f.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public void runMRCScript() {
        try {
            Gson gson=new Gson();
            String json=gson.toJson(qs);
            System.out.println(json);
            File f=new File("tmp.json");
            FileOutputStream fos = new FileOutputStream(f);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(json);
            writer.close();
            fos.close();
            Process proc = Runtime.getRuntime().exec(
                    //"cmd /c mkdir temp");
                    //"conda activate base\n" +
                    //"cmd /c python " + PY + " --question "+question+" --context "+context);
                    "cmd /c python "+"mrc.py");
            int res=proc.waitFor();
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
