    var template = '<div class="preview">'+
                            '<span class="imageHolder">'+
                                '<img />'+
                                '<span class="uploaded"></span>'+
                            '</span>'+
                            '<div class="progressHolder">'+
                                '<div class="progress"></div>'+
                            '</div>'+
                        '</div>';

        function createImage(file){

            var preview = $(template),
                image = $('img', preview);

            var reader = new FileReader();

            image.width = 100;
            image.height = 100;

            reader.onload = function(e){
                image.attr('src',e.target.result);
            };


            reader.readAsDataURL(file);

            message.hide();
            preview.appendTo(dropbox);


            $.data(file,preview);
        }
        $(function(){

            var dropbox = $('#dropbox'),
                message = $('.message', dropbox);

            dropbox.filedrop({
                //用来标识上传文件的数组名
                paramname:'pic',

                             //上传文件个数
                maxfiles: 5,
                maxfilesize: 1023, // 每个文件最大大小,为2MB
                url: 'http://localhost:8086/web/servlet/UploadHandleServlet',

                uploadFinished:function(i,file,response){
                    $.data(file).addClass('done');
                            },

                error: function(err, file) {
                    switch(err) {
                        case 'BrowserNotSupported':
                            showMessage('Your browser does not support HTML5 file uploads!');
                            break;
                        case 'TooManyFiles':
                            alert('Too many files! Please select 5 at most!');
                            break;
                        case 'FileTooLarge':
                            alert(file.name+' is too large! Please upload files up to 2mb.');
                            break;
                        default:
                            break;
                    }
                },

                //判断每个上传的图片是否文件格式
                beforeEach: function(file){
                    if(!file.type.match(/^image\//)){
                        alert('Only images are allowed!');
                            return false;
                    }
                },

                uploadStarted:function(i, file, len){
                    createImage(file);
                },

                progressUpdated: function(i, file, progress) {
                    $.data(file).find('.progress').width(progress);
                }

            });


            function showMessage(msg){
                message.html(msg);
            }
            }
            );
