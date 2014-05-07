
$(document).ready(function(){
    initiliaze();
    millisecondsToTime();
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
            });
        },
        hoverClass: 'case-hover'
    });
    $('.'+color).draggable({
        opacity: 0.8,
        revert:  true,
        scroll:  false,
        cursor: 'move',
        zIndex: 10
    });
    $("#sendMessage").click(function(){
        sendMessage();
    });

}
function update(){
    $.get( "update", function( data ) {
        if($(data).filter("input[name=update]").val()=='true'){
            $("#board").replaceWith($(data).filter("#board"));
            initiliaze();
        }
        if($(".chat").html()!=$(data).filter(".chat").html()){
            $(".chat").html($(data).filter(".chat").html());

        }

        $("#time").text(millisecondsToTime($(data).filter("#time").text()));
        setTimeout(update, 1000);
    });
}


function millisecondsToTime(milli)
{
    if(milli==null){
        var milli = $("#time").html();
    }
    var seconds = Math.floor((milli / 1000) % 60);
    var minutes = Math.floor((milli / (60 * 1000)) % 60);
    if(seconds<10)
    {
        seconds='0'+seconds;
    }
    $("#time").text( minutes + ":" + seconds);
}
function sendMessage()
{
    $.post("sendMessage", {messageText: $("input[name=messageText]").val()});
}



