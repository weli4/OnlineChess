function Piece(name, color)
{
    this.name=name;
    this.color=color;
}
Piece.prototype.getIcon = function(){
    return 'resources/img/pieces'+this.color+'/'+this.name+'.png';
}
