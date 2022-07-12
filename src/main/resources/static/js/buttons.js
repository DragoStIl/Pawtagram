    <script>
        window.onload = function () {
            if(document.getElementById("dog")){
                addEventListener('click', function () {
                window.location.href = 'dogs'
                });
            }
            else if(document.getElementById("cat")){
                addEventListener('click', function () {
                window.location.href = 'cat'
                })
            }
            else(document.getElementById("other"));{
                addEventListener('click', function () {
                window.location.href = 'exotic'
                })
            }
        }
    </script>