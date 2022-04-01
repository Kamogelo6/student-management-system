import fetch from 'unfetch'

const apiUrl = "api/v1/students"

const checkStatus= response => {
    if (response.ok) {
        return response;
    } else {
        const error = new Error(response.statusText);
        error.response = response;
        return Promise.reject(error);
    }
}

export const  getAllStudents = () =>
    fetch(apiUrl)
        .then(checkStatus);

export const addNewStudent = student =>
    fetch(apiUrl, {
        headers : {
            "Content-Type" : "application/json"
        },
        method: "POST",
        body: JSON.stringify(student)
    }).then(checkStatus);


export const deleteStudent = studentId =>
    fetch(`${apiUrl}/${studentId}`, {
        method: "DELETE",
        headers : {
            "Content-Type" : "application/json"
        },
    }).then(checkStatus)


