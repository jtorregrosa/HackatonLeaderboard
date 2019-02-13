/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HackatonLeaderboardTestModule } from '../../../test.module';
import { StageDeleteDialogComponent } from 'app/entities/stage/stage-delete-dialog.component';
import { StageService } from 'app/entities/stage/stage.service';

describe('Component Tests', () => {
    describe('Stage Management Delete Component', () => {
        let comp: StageDeleteDialogComponent;
        let fixture: ComponentFixture<StageDeleteDialogComponent>;
        let service: StageService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HackatonLeaderboardTestModule],
                declarations: [StageDeleteDialogComponent]
            })
                .overrideTemplate(StageDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StageDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StageService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
