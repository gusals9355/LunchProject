<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
  <div>
    <label for="image_uploads">Choose images to upload (PNG, JPG)</label>
    <input type="file" id="image_uploads" name="image_uploads" accept=".jpg, .jpeg, .png" multiple>
  </div>
  <div class="preview">
    <p>선택된 파일이 없습니다</p>
  </div>
  <div>
    <button>Submit</button>
  </div>
  
  <script>
  var input = document.querySelector('input');
  var preview = document.querySelector('.preview');

  input.style.opacity = 0;
  
  input.addEventListener('change', updateImageDisplay);
  
  function updateImageDisplay() {
	  while(preview.firstChild) {
	    preview.removeChild(preview.firstChild);
	  }

	  const curFiles = input.files;
	  if(curFiles.length === 0) {
	    const para = document.createElement('p');
	    para.textContent = 'No files currently selected for upload';
	    preview.appendChild(para);
	  } else {
	    const list = document.createElement('ol');
	    preview.appendChild(list);

	    for(const file of curFiles) {
	      const listItem = document.createElement('li');
	      const para = document.createElement('p');
	      if(validFileType(file)) {
	        const image = document.createElement('img');
	        image.src = URL.createObjectURL(file);

	        listItem.appendChild(image);
	        listItem.appendChild(para);
	      } else {
	        para.textContent = "파일 타입이 올바르지 않습니다. 다시 확인해주세요.";
	        listItem.appendChild(para);
	      }

	      list.appendChild(listItem);
	    }
	  }
	}
  
  const fileTypes = [
	  "image/apng",
	  "image/bmp",
	  "image/gif",
	  "image/jpeg",
	  "image/pjpeg",
	  "image/png",
	  "image/svg+xml",
	  "image/tiff",
	  "image/webp",
	  "image/x-icon"
	];

	function validFileType(file) {
	  return fileTypes.includes(file.type);
	}
	
	function returnFileSize(number) {
		  if(number < 1024) {
		    return number + 'bytes';
		  } else if(number >= 1024 && number < 1048576) {
		    return (number/1024).toFixed(1) + 'KB';
		  } else if(number >= 1048576) {
		    return (number/1048576).toFixed(1) + 'MB';
		  }
	}
  </script>
</form>
</body>
</html>