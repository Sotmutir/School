const http = require('http');
const fs = require('fs');

const port = 9999;


const server = http.createServer((req, res) => {
    if(req.method === 'GET' && req.url === '/') {
        const file = fs.readFileSync('index.html');
        res.writeHead(200, {'Content-Type': 'text/html'});
        
        res.end(file);
    }

    if(req.method === 'GET' && req.url === '/style.css') {
        const file = fs.readFileSync('style.css');
        res.writeHead(200, {'Content-Type': 'text/css'});
        
        res.end(file);
    }

    if(req.method === 'GET' && req.url === '/script.js') {
        const file = fs.readFileSync('script.js');
        res.writeHead(200, {'Content-Type': 'text/javascript'});
        
        res.end(file);
    }

    if(req.method === 'POST' && req.url === '/compile') {
        let string = "";

        req.on('data', (data) => {
            string += data.toString();
        });

        req.on('end', () => {
            try {
                const codes = JSON.parse(string).files;
                let names = "";

                for(i = 0; i < codes.length; i++) {
                    fs.writeFileSync('files/' + codes[i].name, codes[i].code);
                    if(codes[i].name.endsWith('.hpp') || codes[i].name.endsWith('.h')) continue;

                    names += codes[i].name + " ";
                }

                require('child_process').exec('cd files && g++ ' + names + '-o code.exe && code.exe', (error, stdout, stderr) => {
                    res.writeHead(200, {'Content-Type': 'application/json'});
                    if (error) {
                        res.end(JSON.stringify({output: stderr}));
                    } else {
                        res.end(JSON.stringify({output: stdout}));
                    }
                });

            } catch (err) {
                console.log(err);
                res.writeHead(400, {'Content-Type': 'application/json'});
                res.end(JSON.stringify({error: 'Invalid JSON'}));
            }
        });
    }

}).listen(port, () => {
    console.log(`Server is running on port ${port}`);
});