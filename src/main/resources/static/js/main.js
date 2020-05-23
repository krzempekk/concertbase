function changePosition(element){
    element.style.left = "20vw";
}


function scrollVideo(value) {
    let element = document.getElementsByClassName("videoPlayers")[0];
    let change_value = parseInt(value);


    let okienko = element.getBoundingClientRect();
    if (change_value < 0) {
        if (okienko.right < 0.9 * window.innerWidth) {
            console.log("can't scroll any more to right");
        } else {
            let temp_string = element.style.left;
            let current_value = parseInt(temp_string.slice(0, -2)) + change_value;
            element.style.left = current_value + "vw";
        }
    } else {
        if (okienko.left > 0.1 * window.innerWidth) {
            console.log("can't scroll any more to left");
        } else {
            let temp_string = element.style.left;
            let current_value = parseInt(temp_string.slice(0, -2)) + change_value;
            element.style.left = current_value + "vw";
        }
    }
}



