const path = "/atendimentos"
const moment = require("moment")
const { Atendimento } = require("../models")
const { AtendimentoService } = require("../services/atendimentoService")

function validaId(req,res){
    if(!req.params.id){
        res.status(404).json({
            status: 404,
            message: "Id não encontrado",
        })
    }
}

module.exports = app => {
    
    app.get(path, async (req, res) => {

        const atendimentos = await Atendimento.findAll();
        
        await res.status(200).json({
            status: 200,
            message: "Listando todos os atendimentos",
            data: atendimentos
        })
    })


    app.post(path, async (req, res) => {
        const dataAgendamentoTratatada = moment(req.body.dataAgendamento, "DD/MM/YYYY").format("YYYY-MM-DD HH:MM:SS")

        const atendimento = {
            ...req.body,
            dataAgendamento: dataAgendamentoTratatada
        }

        const atendimentoCriado = await Atendimento.create(atendimento);

        res.status(201).json({
            status: 201,
            message: "Atendimento criado com sucesso",
            data: atendimentoCriado
        })
    })

    app.put(path + "/:id", async (req, res) => {

        validaId(req,res);

        const atendimento = await Atendimento.findByPk(req.params.id);

        if(!atendimento){
            res.status(404).json({
                status: 404,
                message: "Atendimento não encontrado",
                data: []
            })
        }

        await Atendimento.update(
            req.body,
            { where: { id: req.params.id } })
            .then(data => res.status(200).json({
                status: 200,
                message: "Atendimento atualizado com sucesso",
                data: req.body
            }))
            .catch(error =>
                console.log(error)
            )
    })

    app.get(path + "/:id", async (req, res) => {

        validaId(req,res)

        const atendimento = await Atendimento.findByPk(req.params.id);

        if(!atendimento){
            res.status(404).json({
                status: 404,
                message: "Atendimento não encontrado",
                data: []
            })
        }

        res.status(200).json({
            status: 200,
            message: "Atendimento encontrado com sucesso",
            data: atendimento
        })
    })

    app.delete(path + "/:id", async(req, res) => {

        validaId(req,res)

        if(!Atendimento.findByPk(req.params.id)){
            res.status(404).json({
                status: 404,
                message: "Atendimento não encontrado",
                data: {id:req.params.id}
            })
        }

        Atendimento.destroy({where:{id:req.params.id}})
        res.send({ status: 200, message: "Atendimento " + req.params.id + " deletado " })
    })

}