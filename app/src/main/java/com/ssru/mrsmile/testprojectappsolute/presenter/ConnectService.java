package com.ssru.mrsmile.testprojectappsolute.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.ssru.mrsmile.testprojectappsolute.model.Department;
import com.ssru.mrsmile.testprojectappsolute.model.Faculty;
import com.ssru.mrsmile.testprojectappsolute.model.Subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Mr.Smile on 11/4/2559.
 */
public class ConnectService extends AsyncTask<Void , Void , List<Faculty>> {

    private HttpURLConnection urlConnection;

    private static String TAG = "ConnectService";

    @Override
    protected List<Faculty> doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();

        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    //No need to implement.
                }
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    //No need to implement.
                }
            }
        };

        // Install the all-trusting trust manager
        try {

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            URL url = new URL("https://mobilemagic.de/cuisine/getFacultyHierarchy");
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");

            JSONObject json = new JSONObject();
            json.put("r_id", 4);
            json.put("l_id", 4);

            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(json.toString());
            wr.flush();

            //display what returns the POST request
            StringBuilder sb = new StringBuilder();
            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

                result = sb;
                br.close();

                Log.d(TAG , urlConnection.getResponseMessage());

            } else {
               Log.d(TAG , urlConnection.getResponseMessage());
            }

        } catch( Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        List<Faculty> faculties = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(result.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                faculties.add(new Faculty(
                        jsonObject.getString("tf_id") ,
                        jsonObject.getString("tf_faculty_name") ,
                        jsonObject.getString("tf_image_file_name") ,
                        null
                ));

                List<Department> departments = new ArrayList<>();

                JSONArray arrayDepartment = jsonObject.getJSONArray("Department");

                for (int j = 0 ; j < arrayDepartment.length() ; j++) {
                    JSONObject objectDepartment = arrayDepartment.getJSONObject(j);

                    departments.add(new Department(
                            objectDepartment.getString("td_id")  ,
                            objectDepartment.getString("td_department_name") ,
                            null
                    ));

                    List<Subject> subjects = new ArrayList<>();

                    JSONArray arraySubject = objectDepartment.getJSONArray("Subject");

                    for (int y = 0; y < arraySubject.length(); y++) {
                        JSONObject objectSubject = arraySubject.getJSONObject(y);

                        subjects.add(new Subject(
                                objectSubject.getString("ts_id") ,
                                objectSubject.getString("ts_subject_name") ,
                                objectSubject.getString("ts_section")
                        ));
                    }

                    departments.get(j).setSubjects(subjects);
                }
                faculties.get(i).setDepartments(departments);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        FacultyService.getIntance().setFaculties(faculties);

        return faculties;
    }
}
