
$(document).ready(function(){
     initiliaze();
    setTimeout(update, 1000);
})
function initiliaze(){
    var color=$("input[name=color]").val();
    $(".cell").droppable({
        drop: function(event, ui) {
            var to = $(this);
            var from = $(ui.draggable);
            $.post( "turn", { from: from.attr('id'), to: to.attr('id') },function( data ) {
                if(data!="false")
                {
                    $("#board").replaceWith($(data).filter("#board"));
                    initiliaze();
                }
                else{
                    alert("wtf");
                }
            });
        },
        hoverClass: 'case-hover'
    });
    $('.'+color).draggable({
        opacity: 0.8,
        revert:  'invalid',
        scroll:  false,
        cursor: 'move',
        zIndex: 10
    });

}
function update(){
    $.get( "update", function( data ) {
        if($(data).filter("input[name=update]").val()=='true'){
            $("#board").replaceWith($(data).filter("#board"));
            initiliaze();
        }
        $("#time").text(millisecondsToTime($(data).filter("#time").text()));
        setTimeout(update, 1000);
    });
}


function millisecondsToTime(milli)
{
    var seconds = Math.floor((milli / 1000) % 60);
    var minutes = Math.floor((milli / (60 * 1000)) % 60);
    return minutes + ":" + seconds;
}


