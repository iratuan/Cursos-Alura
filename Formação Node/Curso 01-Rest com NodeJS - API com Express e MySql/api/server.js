const custonExpress = require("./src/config/customExpress")
const PORT_NUMBER = 3000

app = custonExpress()

app.listen(PORT_NUMBER, ()=>{
    console.log("SERVIDOR STARTADO NA PORTA " + PORT_NUMBER )
})

