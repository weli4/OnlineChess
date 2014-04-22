function Piece(type, color)
{
    this.color = color;
    this.type = type;
}

/**
 * Retourne le chemin vers l'image
 */
Piece.prototype.getPathImage = function()
{
    return 'resources/img/pieces/' + this.color + '/' + this.type + '.png';
}

// Objet board

var board = new Object();

// La couleur de l'utilisateur
board.userColor = undefined;

// Si le joueur peut jouer
board.canPlay = undefined;

board.resize = function()
{
    var width = 0.95 * $(document).width();
    var height = 0.75 * $(document).height();

    var min = Math.min(width, height);

    // La taille doit être un multiple de 8
    if (min % 8 != 0) {
        min = min - (min % 8);
    }

    // Changement de la taille du plateau
    $('#board').width(min);
    $('#board').height(min);

    // Changement de la taille de chaque div dans le plateau
    $('#board > div').width(min / 8);
    $('#board > div').height(min / 8);
}

board.movePiece = function(src, dst)
{
    var img = src.children();

    if (img != null) {
        dst.children().remove();
        dst.append(img);
    }
}


board.move = function(src, dst, obj)
{
    obj.css({
        position: 'relative',
        left: 0,
        top: 0
    });

    if (dst.children().length == 1 && dst.children().hasClass('piece_' + board.userColor)) {
        // L'utilisateur essaye de placer une pièce
        // sa pièce sur une case à lui !
        return;
    }

    // Il est nécessaire de supprimer l'ensemble des fils de la case
    dst.children().remove();

    dst.append(obj);

    // On informe le serveur du changement sur le plateau
    $.get('move', {src: src.attr('id').substr(1), dst: dst.attr('id').substr(1)}, function(xml) {
        var xmlDoc = $(xml);
        var state = xmlDoc.find('state');

        if (state.text() == 'ok') {
            // Le mouvement est bon
            chess.switchPlayer();
        } else {
            // Il ne l'est pas, il faut annuler le dernier mouvement
            var xmlPiece = xmlDoc.find('piece');
            var piece = null;

            if (xmlPiece.length != 0) {
                piece = new Piece();
                piece.color = xmlPiece.find('color').text();
                piece.type = xmlPiece.find('type').text();
            }

            board.undoLastMove(src, dst, piece);
        }
    });
}

board.undoLastMove = function(src, dst, obj)
{
    // On retourne sur la case de départ
    board.movePiece(dst, src);

    if (obj != null) {
        board.addPiece(dst.attr('id').substr(1), obj)
    }
}

board.addPiece = function(position, piece)
{
    var _case = $('#_' + position);

    if (_case == null) {
        return;
    }

    var img = $(document.createElement('img'));

    img.attr({
        src:   piece.getPathImage(),
        title: piece.type
    });

    img.addClass('piece_' + piece.color);
    img.addClass('piece_' + piece.type);

    img.appendTo(_case);
}

board.disableDragAndDrop = function()
{
    if (board.userColor == undefined) {
        return;
    }

    $('#board > div > img.piece_' + board.userColor).draggable("option", "disabled", true);
}


board.enableDragAndDrop = function()
{
    if (board.userColor == undefined) {
        return;
    }

    $('#board > div > img.piece_' + board.userColor).draggable("option", "disabled", false);
}

board.disable = function()
{
    // L'utilisateur n'est plus capable de bouger ses pièces
    board.disableDragAndDrop();

    // Une opacité de 50% pour faire cool !
    $("#board > div > img").css({opacity: 0.5});
}

board.initialize = function()
{
    var pieces = new Array();

    // Black
    pieces['8A'] = new Piece('rook', 'black');
    pieces['8B'] = new Piece('knight', 'black');
    pieces['8C'] = new Piece('bishop', 'black');
    pieces['8D'] = new Piece('queen', 'black');
    pieces['8E'] = new Piece('king', 'black');
    pieces['8F'] = new Piece('bishop', 'black');
    pieces['8G'] = new Piece('knight', 'black');
    pieces['8H'] = new Piece('rook', 'black');

    pieces['7A'] = new Piece('pawn', 'black');
    pieces['7B'] = new Piece('pawn', 'black');
    pieces['7C'] = new Piece('pawn', 'black');
    pieces['7D'] = new Piece('pawn', 'black');
    pieces['7E'] = new Piece('pawn', 'black');
    pieces['7F'] = new Piece('pawn', 'black');
    pieces['7G'] = new Piece('pawn', 'black');
    pieces['7H'] = new Piece('pawn', 'black');

    // White
    pieces['2A'] = new Piece('pawn', 'white');
    pieces['2B'] = new Piece('pawn', 'white');
    pieces['2C'] = new Piece('pawn', 'white');
    pieces['2D'] = new Piece('pawn', 'white');
    pieces['2E'] = new Piece('pawn', 'white');
    pieces['2F'] = new Piece('pawn', 'white');
    pieces['2G'] = new Piece('pawn', 'white');
    pieces['2H'] = new Piece('pawn', 'white');

    pieces['1A'] = new Piece('rook', 'white');
    pieces['1B'] = new Piece('knight', 'white');
    pieces['1C'] = new Piece('bishop', 'white');
    pieces['1D'] = new Piece('queen', 'white');
    pieces['1E'] = new Piece('king', 'white');
    pieces['1F'] = new Piece('bishop', 'white');
    pieces['1G'] = new Piece('knight', 'white');
    pieces['1H'] = new Piece('rook', 'white');

    for (position in pieces) {
        board.addPiece(position, pieces[position]);
    }

    $('#board > div > img.piece_' + board.userColor).draggable({
        opacity: 0.8,
        revert:  'invalid',
        scroll:  false,
        cursor: 'move',
        zIndex: 10
    });

    $("#board > div").droppable({
        drop: function(event, ui) {
            var dst = $(this);
            var src = $(ui.draggable).parent();
            var obj = $(ui.draggable);

            // On bouge l'objet
            board.move(src, dst, obj);
        },
        hoverClass: 'case-hover'
    });
}

$(document).ready(function() {
    board.resize();
});

$(window).resize(function () {
    board.resize();
});
