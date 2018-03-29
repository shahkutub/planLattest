package com.nanosoft.planInternational.tracking.utility;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadToServer {

    int serverResponseCode = 0;

    String upLoadServerUri = null;


    public void UploadToServer(String upLoadServerUri) {
        this.upLoadServerUri = upLoadServerUri;
    }

    public int uploadFile(byte[] imgBytes, String fileName, int scId) {
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        if (imgBytes != null) {

            Log.e("Err", "Not found");
            return 0;
        } else {
            try {

                // open a URL connection to the Servlet
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name='uploaded_file';filename='"
                        + fileName + "'" + lineEnd);
                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bufferSize = Math.min(imgBytes.length, maxBufferSize);
                buffer = new byte[bufferSize];
                // read file and write it into form...
                InputStream stream = new ByteArrayInputStream(imgBytes);

                bytesRead = stream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = stream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = stream.read(buffer, 0, bufferSize);
                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if (serverResponseCode == 200) {
                }

                //close the streams //
                stream.close();
                dos.flush();
                dos.close();

            } catch (Exception e) {
            }
            return serverResponseCode;
        } // End else block
    }
}