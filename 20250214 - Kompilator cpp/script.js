const form = document.getElementById('form');
const save = document.getElementById('save');

var currFile = "Main.cpp";
const code = document.getElementById('code');
const output = document.getElementById('output');

const filesTree = document.getElementById('files-tree');
var files = filesTree.children;

var codes = [];

form.addEventListener('submit', (e) => {
    e.preventDefault();

    files = filesTree.children;
    for(i = 0; i < files.length; i++) {
        if(files[i].innerText === currFile) {
            codes[i].code = code.value;
            break;
        }
    }
    
    const data = {
        files: codes,
    }

    localStorage.setItem("codes", JSON.stringify(data));

    output.innerText = "";

    fetch('/compile', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json()).then(data => {
        output.innerText = data.output;
    });
});



save.addEventListener('click', e => {
    files = filesTree.children;
    for(i = 0; i < files.length; i++) {
        if(files[i].innerText === currFile) {
            codes[i].code = code.value;
            break;
        }
    }

    const data = {
        files: codes,
    }
    localStorage.setItem("codes", JSON.stringify(data));
});




if(!localStorage.getItem('codes')) {
    codes = [
        {name: "Main.cpp", code: ""}
    ];
} else {
    codes = JSON.parse(localStorage.getItem('codes')).files;

    filesTree.innerHTML = "";

    codes.forEach((file) => {
        const newFile = document.createElement('div');
        newFile.classList.add('file');
        newFile.innerText = file.name;

        newFile.addEventListener('click', (e) => {
            codes.forEach((file) => {
                if(file.name === currFile) {
                    file.code = code.value;
                }
            });

            currFile = e.target.innerText;
            select();
        });
        
        filesTree.appendChild(newFile);
    });
}
currFile = codes[0].name;
select();



function select() {
    files = filesTree.children;

    for(i = 0; i < files.length; i++) {
        if(files[i].innerText === currFile) {
            files[i].classList.add('selected');
            code.value = codes[i].code;

        } else {
            files[i].classList.remove('selected');
        }
    }
}

const add = document.getElementById('add-file');


add.addEventListener('click', () => {
    const name = prompt("Enter file name:");

    if(!(name.endsWith('.cpp') || name.endsWith('.h') || name.endsWith('.hpp'))) return alert("Invalid file name!");
    
    const newFile = document.createElement('div');
    newFile.classList.add('file');
    newFile.innerText = name;
    filesTree.appendChild(newFile);
    
    codes.forEach((file) => {
        if(file.name === currFile) {
            file.code = code.value;
        }
    });

    codes.push({
        name: name,
        code: ""
    });

    newFile.addEventListener('click', (e) => {
        codes.forEach((file) => {
            if(file.name === currFile) {
                file.code = code.value;
            }
        });

        currFile = e.target.innerText;
        select();
    });

    currFile = name;
    select();
});

const remove = document.getElementById('remove-file');
remove.addEventListener('click', () => {
    if(confirm("Are you sure you want to delete this file?")) {
        files = filesTree.children;

        for(i = 0; i < files.length; i++) {
            if(files[i].innerText === currFile) {
                filesTree.removeChild(files[i]);
                codes.splice(i, 1);
                currFile = "Main.cpp";
                select();
                break;
            }
        }
    }
});



window.addEventListener('keydown', (e) => {
    if(e.key === 'Tab') {
        e.preventDefault();
        const start = code.selectionStart;
        const end = code.selectionEnd;

        code.value = code.value.substring(0, start) + '\t' + code.value.substring(end);

        code.selectionStart = code.selectionEnd = start + 1;
        
        localStorage.setItem('codes', JSON.stringify({files: codes}));
    }
});