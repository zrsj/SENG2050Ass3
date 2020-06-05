function uploadCheck(){
    if(document.getElementById("fileupload").files.length == 0){
        console.log("No file has been uploaded.");
        return false;
    }
}
