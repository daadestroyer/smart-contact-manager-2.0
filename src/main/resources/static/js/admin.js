console.log('admin script running')


document.querySelector("#imageFileInput").addEventListener('change',function(event){
    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onload = function(){
        document.getElementById("uploadImagePreview").src=reader.result;
    }
    reader.readAsDataURL(file);
});