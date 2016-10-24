//
//  ViewController.swift
//  Girth
//
//  Created by 诸葛俊伟 on 10/23/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import UIKit
import SnapKit

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.addSubview(startBtn)
        
        startBtn.snp.makeConstraints { (make) in
            make.center.equalTo(view)
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @objc fileprivate func didTappedStartBtn(_ sender: UIButton) {
        self.present(CharViewController(), animated: true, completion: nil)
    }
    
    fileprivate lazy var startBtn: UIButton = {
        var btn = UIButton(type: .custom)
        btn.setTitle("Girth", for: .normal)
        btn.backgroundColor = UIColor.cyan
        btn.setBackgroundImage(UIImage(named: "h"), for: UIControlState())
        btn.setTitleColor(UIColor.brown, for: UIControlState())
        btn.addTarget(self, action: #selector(ViewController.didTappedStartBtn(_:)), for: .touchUpInside)
        btn.layer.cornerRadius = 30
        btn.layer.masksToBounds = true
        return btn
    }()
}

