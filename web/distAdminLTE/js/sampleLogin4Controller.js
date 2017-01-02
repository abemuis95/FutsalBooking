/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    // Form Validation
    $('#form-validation').validate({
        submit: {
            settings: {
                inputContainer: '.form-group',
                errorListClass: 'form-control-error',
                errorClass: 'has-danger'
            }
        }
    });

    // Show/Hide Password
    $('.password').password({
        eyeClass: '',
        eyeOpenClass: 'icmn-eye',
        eyeCloseClass: 'icmn-eye-blocked'
    });

    $('#loginRadisAppCancel').click(function () {
        $('#errormsg').hide();
        $('#errormessage').hide();
        $('#loginMainForm').attr('action', '/sample/sampleUmum.do?method=mtdLoginCancel');
        $('#loginMainForm').submit();
    });

    $('#loginRadisApp').click(function () {
        var errorNo = eval($('#errorNo').val());
        var bypassChecking = 'true';

        if (errorNo !== '' && !isNaN(errorNo)) {
            if (errorNo < 0)
                $('#errormsg').show();
        } else {
            $('#errormsg').hide();
        }

        if ($('#usernameID').val() === "") {
            bypassChecking = 'false';
        }

        if ($('#passwordID').val() === "") {
            bypassChecking = 'false';
        }

        if (bypassChecking === 'true') {
            $('#errormessage').hide();
            $('#errormsg').hide();
            $('#loginMainForm').attr('action', '/sample/authentication.do');
            $('#loginMainForm').submit();
        } else {
            $('#errormsg').hide();
            $('#errormessage').fadeIn(2000).fadeOut(400).fadeIn(2000).fadeOut(400);;
            $('#username').val('');
            $('#password').val('');

        }
    });

    // Add class to body for change layout settings
        //$('body').addClass('full-height');

        // Set Background Image for Form Block
        function setImage() {
            var imgUrl = $('.page-content-inner').css('background-image');

            $('.blur-placeholder').css('background-image', imgUrl);
        };

        function changeImgPositon() {
            var width = $(window).width(),
                    height = $(window).height(),
                    left = - (width - $('.single-page-block-inner').outerWidth()) / 2,
                    top = - (height - $('.single-page-block-inner').outerHeight()) / 2;


            $('.blur-placeholder').css({
                width: width,
                height: height,
                left: left,
                top: top
            });
        };

        setImage();
        changeImgPositon();

        $(window).on('resize', function(){
            changeImgPositon();
        });

        // Mouse Move 3d Effect
        var rotation = function(e){
            var perX = (e.clientX/$(window).width())-0.5;
            var perY = (e.clientY/$(window).height())-0.5;
            TweenMax.to(".effect-3d-element", 0.4, { rotationY:15*perX, rotationX:15*perY,  ease:Linear.easeNone, transformPerspective:1000, transformOrigin:"center" })
        };

        if (!cleanUI.hasTouch) {
            $('body').mousemove(rotation);
        }
});



