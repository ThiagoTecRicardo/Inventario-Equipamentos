import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { Equipamento } from '../../services/equipamento';  


@Component({
  selector: 'app-equipamento-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './equipamento-list.html',
  styleUrl: './equipamento-list.css',
})
export class EquipamentoList implements OnInit {
 
  public service = inject(Equipamento);

   ngOnInit(): void {
    this.service.carregarTodos();
   }
  }