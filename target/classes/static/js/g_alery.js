var cont = 1;

$(document).ready(()=>{
    $('.mostrar').hide();
    while(cont<11){
        var escolher = '.prato'+cont;
        $(escolher).show();
        cont++;
    }
    $('.btnMostrarMais').click(()=>{
        var alter = cont+10;
        while(cont<alter){
            var escolher = '.prato'+cont;
            $(escolher).show();
            cont++;
        }
    });
});

