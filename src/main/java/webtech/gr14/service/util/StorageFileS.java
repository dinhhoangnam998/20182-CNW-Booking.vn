package webtech.gr14.service.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageFileS {

	public String saveFile(MultipartFile mtpf, String dir, String name) {
		String fileName = name + mtpf.getOriginalFilename().substring(mtpf.getOriginalFilename().lastIndexOf("."));

		File dest = new File("H:/booking-upload/" + dir + "/" + fileName);

		try {
			mtpf.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileName;
	}
}
