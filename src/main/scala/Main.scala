import java.io._

import org.apache.commons._
import org.apache.http._
import org.apache.http.client._
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import java.util.{ArrayList, Base64}

import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.entity.StringEntity
import org.apache.http.util.EntityUtils
import java.nio.file.{Files, Paths}

import scala.io.Source

object Main {

  def main(args: Array[String]) {

    val byteArray = Files.readAllBytes(Paths.get(getClass.getResource("2.jpg").getPath))

    val imageContent = Base64.getEncoder.encodeToString(byteArray)

    val stockAsJson = "{\"requests\": [{\"features\": [{\"type\": \"LABEL_DETECTION\"}," +
      "{\"type\": \"IMAGE_PROPERTIES\"}," +
      "{\"type\": \"FACE_DETECTION\"}," +
      "{\"type\": \"SAFE_SEARCH_DETECTION\"}," +
      "{\"type\": \"TEXT_DETECTION\"}," +
      "{\"type\": \"LOGO_DETECTION\"}," +
      "{\"type\": \"LANDMARK_DETECTION\"}]" + //TYPE_UNSPECIFIED
      ",\"image\": {\"content\": \"" + imageContent + "\"}}]}"


    val api_key = Source.fromFile("GoogleVisionAPI.properties")
      .getLines()
      .find(_.startsWith("secret_key="))
      .get
      .split("=")(1)

    val url = "https://vision.googleapis.com/v1/images:annotate?key="+api_key
    val client = new DefaultHttpClient

    val post = new HttpPost(url)

    post.setEntity(new StringEntity(stockAsJson))

    val response = client.execute(post)
    println("--- HEADERS ---")
    response.getAllHeaders.foreach(arg => println(arg))
    println("--- RESPONSE ---")
    println(EntityUtils.toString(response.getEntity, "UTF-8"))
  }

}