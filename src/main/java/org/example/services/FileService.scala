package org.example.services

import org.example.exceptions.ApiException
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.io.{File, IOException}

@Service
class FileService {

  //Get directory to save from app.props
  @Value("${file.upload-dir}")
  var uploadDir: String = _

  def ensureBaseDir(): Unit = {
    val dir = new File(uploadDir)
    if(!dir.exists()){
      dir.mkdirs()
    }
  }

  // Save file to disk and return pathForNewFile
  def saveFile(file: MultipartFile): String = {
    ensureBaseDir()

    val destinationFile = new File(uploadDir, file.getOriginalFilename)

    try{
      // Save file and return file path
      file.transferTo(destinationFile)
      destinationFile.getAbsolutePath
    } catch {
      case ex: IOException =>
        throw new ApiException(s"Failed to save file: ${ex.getMessage}")
    }
  }
}
