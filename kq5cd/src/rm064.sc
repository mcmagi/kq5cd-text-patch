;;; Sierra Script 1.0 - (do not remove this comment)
;;; Decompiled by sluicebox
(script# 64)
(include sci.sh)
(use Main)
(use Interface)
(use castle)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm064 0
)

(local
	local0
	local1
	[local2 56] = [147 0 148 138 64 138 36 155 35 164 15 167 22 182 62 184 128 184 150 187 304 183 305 162 301 159 286 157 269 152 260 149 268 143 265 138 258 138 249 146 207 146 199 138 173 138 162 0 319 0 319 189 0 189 0 0]
)

(instance rm064 of KQ5Room
	(properties
		picture 64
		horizon 135
		north 63
	)

	(method (init)
		(super init:)
		(Load rsVIEW 706)
		(Load rsVIEW 710)
		(= global357 274)
		(= global358 166)
		(= global355 79)
		(= global356 156)
		(self
			setRegions: 550 ; castle
			setFeatures: desk shelves doorWay room
			addObstacle: poly1
			setScript: enterNorth
		)
		(glow1 setCycle: Fwd init:)
		(eyeBall init: stopUpd:)
		(book init: stopUpd:)
		(poly1 points: @local2 size: 28)
		(switch global331
			(5
				((ScriptID 550 7) init: setScript: wizSleepingScript) ; theWizard
			)
			(7
				(= global352 2)
				(= global359 0)
			)
		)
	)

	(method (doit &tmp temp0)
		(cond
			(script
				(script doit:)
			)
			((and (gEgo inRect: 77 147 260 171) (== global331 3))
				(if (< (gEgo x:) 158)
					(= global349 263)
					(= global350 176)
					(= global351 315)
					(= global354 135)
				else
					(= global349 72)
					(= global350 145)
					(= global351 135)
					(= global354 225)
				)
				((ScriptID 550 7) init: setScript: (ScriptID 550 12)) ; theWizard, theWizardScript
			)
			(
				(and
					(gEgo edgeHit:)
					(== 63 (self edgeToRoom: (gEgo edgeHit:)))
				)
				(if (and (== global331 5) (== global332 6))
					(= global331 6)
					(= global353 0)
					(= global352 0)
					(= global359 0)
				)
				(gCurRoom newRoom: 63)
			)
			((and global359 (not (IsFlag 64)) (!= local1 (GetTime 1))) ; SysTime12
				(= local1 (GetTime 1)) ; SysTime12
				(if (and (not (-- global359)) (== global331 0))
					(= global331 5)
					((ScriptID 550 7) init: setScript: zzzScript) ; theWizard
				)
			)
		)
		(if (not local0)
			(if (> (gEgo y:) 175)
				(gEgo setPri: 13)
			else
				(gEgo setPri: -1)
			)
		)
	)

	(method (handleEvent event)
		(cond
			((event claimed:)
				(return)
			)
			(script
				(return)
			)
		)
	)

	(method (dispose)
		(super dispose:)
	)
)

(instance glow1 of Prop
	(properties
		x 54
		y 153
		view 710
		loop 2
		priority 15
		signal 16400
		cycleSpeed 4
		detailLevel 3
	)
)

(instance eyeBall of Prop
	(properties
		x 159
		y 28
		view 710
		loop 6
		cel 2
		priority 15
		signal 16400
		detailLevel 3
	)

	(method (doit &tmp temp0 temp1)
		(super doit:)
		(if (>= (gGame detailLevel:) detailLevel)
			(= temp1 (eyeBall cel:))
			(= temp0 (eyeBall loop:))
			(cond
				((and (== temp1 2) (== temp0 6) (> (gEgo y:) 160))
					(eyeBall setCycle: Beg self)
				)
				((and (== temp1 0) (== temp0 6) (> (gEgo y:) 160))
					(eyeBall loop: 5)
				)
				((== temp0 5)
					(if (< (gEgo y:) 160)
						(eyeBall cel: 0 loop: 6 setCycle: End)
					else
						(eyeBall cel: (/ (gEgo x:) 70))
					)
				)
			)
		)
	)
)

(instance shelves of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0004))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 658)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 663)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay of RFeature
	(properties
		nsTop 52
		nsLeft 137
		nsBottom 137
		nsRight 185
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (MousedOn self event))
				(not (== (event type:) evVERB))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 659)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (MousedOn self event))
				(not (== (event type:) evVERB))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(Say 660)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance enterNorth of Script
	(properties)

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(proc550_17)
				(gEgo posn: 158 130 init: setMotion: PolyPath 158 142 self)
			)
			(1
				(proc550_18)
				(client setScript: 0)
			)
		)
	)
)

(instance book of Prop
	(properties
		x 110
		y 179
		view 710
		loop 3
		cel 1
		priority 14
		signal 16400
	)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (MousedOn self event))
				(not (== (event type:) evVERB))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(if (not (IsFlag 21))
						(Say 661)
					else
						(Say 662)
					)
					(event claimed: 1)
				)
				(3 ; Do
					(if (not (IsFlag 21))
						(SetFlag 21)
						(proc550_17)
						(gCurRoom setScript: lookBook)
					else
						(Say 664)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance lookBook of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DoAudio audWPLAY 7068)
				(= register 4)
				(= local0 1)
				(gEgo
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 110 179 self
				)
			)
			(1
				((gEgo head:) hide:)
				(book hide:)
				(-- register)
				(gGlobalAudio number: 7068 loop: 1 play:)
				(gEgo
					normal: 0
					view: 710
					setLoop: 0
					cycleSpeed: 2
					cel: 0
					setPri: 15
					setCycle: End self
				)
				(if register
					(-- state)
				)
			)
			(2
				(gEgo setLoop: 1 cel: 0 setCycle: Fwd cycleSpeed: 12)
				(= seconds 3)
			)
			(3
				(gCast eachElementDo: #hide)
				(gCurRoom drawPic: 219)
				(SetScore 3)
				(Say 665 self)
			)
			(4
				(gCast eachElementDo: #show)
				(gCurRoom drawPic: 64)
				(gEgo
					view: 0
					normal: 1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					setLoop: -1
					setPri: 13
					posn: 105 180
					ignoreActors: 0
					illegalBits: $8000
				)
				(= cycles 5)
			)
			(5
				(SetFlag 21)
				(proc550_18)
				(= local0 0)
				(client setScript: 0)
			)
		)
	)
)

(instance zzzScript of Script
	(properties)

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(proc550_17)
				((ScriptID 550 7) ; theWizard
					view: 706
					setLoop: 8
					posn: 159 105
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(1
				(= cycles (Random 5 10))
			)
			(2
				((ScriptID 550 7) setLoop: 7 cel: 0 setCycle: End self) ; theWizard
			)
			(3
				(= cycles (Random 5 10))
			)
			(4
				((ScriptID 550 7) setLoop: 6 setCel: 255 setCycle: Beg self) ; theWizard
			)
			(5
				(= global353 180)
				(proc550_18)
				((ScriptID 550 7) setLoop: 5) ; theWizard
				(= cycles 1)
			)
			(6
				(if (not (Random 0 3))
					(if (Random 0 1)
						((ScriptID 550 7) setCycle: End) ; theWizard
					else
						((ScriptID 550 7) setCycle: Beg) ; theWizard
					)
				)
				(= seconds (Random 2 6))
				(= state 5)
				(if (not global353)
					(= state 6)
				)
			)
			(7
				(proc550_17)
				((ScriptID 550 7) setLoop: 6 cel: 0 setCycle: End self) ; theWizard
			)
			(8
				(= cycles (Random 5 10))
			)
			(9
				((ScriptID 550 7) setLoop: 7 cel: 7 setCycle: End self) ; theWizard
			)
			(10
				(= cycles (Random 5 10))
			)
			(11
				((ScriptID 550 7) setLoop: 8 cel: 11 setCycle: Beg self) ; theWizard
			)
			(12
				(proc550_18)
				(client setScript: 0)
			)
		)
	)
)

(instance wizSleepingScript of Script
	(properties)

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				((ScriptID 550 7) ; theWizard
					view: 706
					setLoop: 5
					posn: 159 105
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(1
				(if (not (Random 0 3))
					(if (Random 0 1)
						((ScriptID 550 7) setCycle: End) ; theWizard
					else
						((ScriptID 550 7) setCycle: Beg) ; theWizard
					)
				)
				(= seconds (Random 2 6))
				(= state 0)
			)
		)
	)
)

(instance wakeUpScript of Script ; UNUSED
	(properties)

	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				((ScriptID 550 7) setLoop: 6 cel: 0 setCycle: End self) ; theWizard
			)
			(1
				((ScriptID 550 7) setLoop: 7 setCel: 255 setCycle: Beg self) ; theWizard
			)
			(2
				((ScriptID 550 7) setLoop: 8 setCel: 255 setCycle: Beg self) ; theWizard
			)
			(3
				((ScriptID 550 7) posn: 1000 1000) ; theWizard
			)
		)
	)
)

(instance desk of RFeature
	(properties)

	(method (handleEvent event)
		(if
			(or
				(event claimed:)
				(not (== (event type:) evVERB))
				(not (& (OnControl CONTROL (event x:) (event y:)) $0002))
			)
			(return)
		else
			(switch (event message:)
				(2 ; Look
					(if (not (IsFlag 21))
						(Say 661)
					else
						(Say 662)
					)
					(event claimed: 1)
				)
				(3 ; Do
					(Say 666)
					(event claimed: 1)
				)
			)
		)
	)
)

